package com.ramurame.handicraft.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ramurame.handicraft.data.local.entity.OrderEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class OrderDao_Impl implements OrderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<OrderEntity> __insertionAdapterOfOrderEntity;

  private final EntityDeletionOrUpdateAdapter<OrderEntity> __deletionAdapterOfOrderEntity;

  private final EntityDeletionOrUpdateAdapter<OrderEntity> __updateAdapterOfOrderEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllOrders;

  public OrderDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOrderEntity = new EntityInsertionAdapter<OrderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `orders` (`id`,`customerName`,`customerPhone`,`shippingAddress`,`items`,`totalPrice`,`customNote`,`currentStatus`,`orderDate`,`estimatedCompletionDate`,`statusHistory`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final OrderEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getCustomerName());
        statement.bindString(3, entity.getCustomerPhone());
        statement.bindString(4, entity.getShippingAddress());
        statement.bindString(5, entity.getItems());
        statement.bindLong(6, entity.getTotalPrice());
        if (entity.getCustomNote() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCustomNote());
        }
        statement.bindString(8, entity.getCurrentStatus());
        statement.bindLong(9, entity.getOrderDate());
        statement.bindLong(10, entity.getEstimatedCompletionDate());
        statement.bindString(11, entity.getStatusHistory());
      }
    };
    this.__deletionAdapterOfOrderEntity = new EntityDeletionOrUpdateAdapter<OrderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `orders` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final OrderEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfOrderEntity = new EntityDeletionOrUpdateAdapter<OrderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `orders` SET `id` = ?,`customerName` = ?,`customerPhone` = ?,`shippingAddress` = ?,`items` = ?,`totalPrice` = ?,`customNote` = ?,`currentStatus` = ?,`orderDate` = ?,`estimatedCompletionDate` = ?,`statusHistory` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final OrderEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getCustomerName());
        statement.bindString(3, entity.getCustomerPhone());
        statement.bindString(4, entity.getShippingAddress());
        statement.bindString(5, entity.getItems());
        statement.bindLong(6, entity.getTotalPrice());
        if (entity.getCustomNote() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCustomNote());
        }
        statement.bindString(8, entity.getCurrentStatus());
        statement.bindLong(9, entity.getOrderDate());
        statement.bindLong(10, entity.getEstimatedCompletionDate());
        statement.bindString(11, entity.getStatusHistory());
        statement.bindString(12, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllOrders = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM orders";
        return _query;
      }
    };
  }

  @Override
  public Object insertOrder(final OrderEntity order, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfOrderEntity.insert(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertOrders(final List<OrderEntity> orders,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfOrderEntity.insert(orders);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOrder(final OrderEntity order, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfOrderEntity.handle(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateOrder(final OrderEntity order, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfOrderEntity.handle(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllOrders(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllOrders.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllOrders.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<OrderEntity>> getAllOrders() {
    final String _sql = "SELECT * FROM orders ORDER BY orderDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"orders"}, new Callable<List<OrderEntity>>() {
      @Override
      @NonNull
      public List<OrderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerName = CursorUtil.getColumnIndexOrThrow(_cursor, "customerName");
          final int _cursorIndexOfCustomerPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "customerPhone");
          final int _cursorIndexOfShippingAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "shippingAddress");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final int _cursorIndexOfCustomNote = CursorUtil.getColumnIndexOrThrow(_cursor, "customNote");
          final int _cursorIndexOfCurrentStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStatus");
          final int _cursorIndexOfOrderDate = CursorUtil.getColumnIndexOrThrow(_cursor, "orderDate");
          final int _cursorIndexOfEstimatedCompletionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedCompletionDate");
          final int _cursorIndexOfStatusHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "statusHistory");
          final List<OrderEntity> _result = new ArrayList<OrderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final OrderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpCustomerName;
            _tmpCustomerName = _cursor.getString(_cursorIndexOfCustomerName);
            final String _tmpCustomerPhone;
            _tmpCustomerPhone = _cursor.getString(_cursorIndexOfCustomerPhone);
            final String _tmpShippingAddress;
            _tmpShippingAddress = _cursor.getString(_cursorIndexOfShippingAddress);
            final String _tmpItems;
            _tmpItems = _cursor.getString(_cursorIndexOfItems);
            final long _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getLong(_cursorIndexOfTotalPrice);
            final String _tmpCustomNote;
            if (_cursor.isNull(_cursorIndexOfCustomNote)) {
              _tmpCustomNote = null;
            } else {
              _tmpCustomNote = _cursor.getString(_cursorIndexOfCustomNote);
            }
            final String _tmpCurrentStatus;
            _tmpCurrentStatus = _cursor.getString(_cursorIndexOfCurrentStatus);
            final long _tmpOrderDate;
            _tmpOrderDate = _cursor.getLong(_cursorIndexOfOrderDate);
            final long _tmpEstimatedCompletionDate;
            _tmpEstimatedCompletionDate = _cursor.getLong(_cursorIndexOfEstimatedCompletionDate);
            final String _tmpStatusHistory;
            _tmpStatusHistory = _cursor.getString(_cursorIndexOfStatusHistory);
            _item = new OrderEntity(_tmpId,_tmpCustomerName,_tmpCustomerPhone,_tmpShippingAddress,_tmpItems,_tmpTotalPrice,_tmpCustomNote,_tmpCurrentStatus,_tmpOrderDate,_tmpEstimatedCompletionDate,_tmpStatusHistory);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<OrderEntity>> getOrdersByStatus(final String status) {
    final String _sql = "SELECT * FROM orders WHERE currentStatus = ? ORDER BY orderDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, status);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"orders"}, new Callable<List<OrderEntity>>() {
      @Override
      @NonNull
      public List<OrderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerName = CursorUtil.getColumnIndexOrThrow(_cursor, "customerName");
          final int _cursorIndexOfCustomerPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "customerPhone");
          final int _cursorIndexOfShippingAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "shippingAddress");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final int _cursorIndexOfCustomNote = CursorUtil.getColumnIndexOrThrow(_cursor, "customNote");
          final int _cursorIndexOfCurrentStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStatus");
          final int _cursorIndexOfOrderDate = CursorUtil.getColumnIndexOrThrow(_cursor, "orderDate");
          final int _cursorIndexOfEstimatedCompletionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedCompletionDate");
          final int _cursorIndexOfStatusHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "statusHistory");
          final List<OrderEntity> _result = new ArrayList<OrderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final OrderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpCustomerName;
            _tmpCustomerName = _cursor.getString(_cursorIndexOfCustomerName);
            final String _tmpCustomerPhone;
            _tmpCustomerPhone = _cursor.getString(_cursorIndexOfCustomerPhone);
            final String _tmpShippingAddress;
            _tmpShippingAddress = _cursor.getString(_cursorIndexOfShippingAddress);
            final String _tmpItems;
            _tmpItems = _cursor.getString(_cursorIndexOfItems);
            final long _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getLong(_cursorIndexOfTotalPrice);
            final String _tmpCustomNote;
            if (_cursor.isNull(_cursorIndexOfCustomNote)) {
              _tmpCustomNote = null;
            } else {
              _tmpCustomNote = _cursor.getString(_cursorIndexOfCustomNote);
            }
            final String _tmpCurrentStatus;
            _tmpCurrentStatus = _cursor.getString(_cursorIndexOfCurrentStatus);
            final long _tmpOrderDate;
            _tmpOrderDate = _cursor.getLong(_cursorIndexOfOrderDate);
            final long _tmpEstimatedCompletionDate;
            _tmpEstimatedCompletionDate = _cursor.getLong(_cursorIndexOfEstimatedCompletionDate);
            final String _tmpStatusHistory;
            _tmpStatusHistory = _cursor.getString(_cursorIndexOfStatusHistory);
            _item = new OrderEntity(_tmpId,_tmpCustomerName,_tmpCustomerPhone,_tmpShippingAddress,_tmpItems,_tmpTotalPrice,_tmpCustomNote,_tmpCurrentStatus,_tmpOrderDate,_tmpEstimatedCompletionDate,_tmpStatusHistory);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getOrderById(final String orderId,
      final Continuation<? super OrderEntity> $completion) {
    final String _sql = "SELECT * FROM orders WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, orderId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<OrderEntity>() {
      @Override
      @Nullable
      public OrderEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerName = CursorUtil.getColumnIndexOrThrow(_cursor, "customerName");
          final int _cursorIndexOfCustomerPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "customerPhone");
          final int _cursorIndexOfShippingAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "shippingAddress");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final int _cursorIndexOfCustomNote = CursorUtil.getColumnIndexOrThrow(_cursor, "customNote");
          final int _cursorIndexOfCurrentStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStatus");
          final int _cursorIndexOfOrderDate = CursorUtil.getColumnIndexOrThrow(_cursor, "orderDate");
          final int _cursorIndexOfEstimatedCompletionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedCompletionDate");
          final int _cursorIndexOfStatusHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "statusHistory");
          final OrderEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpCustomerName;
            _tmpCustomerName = _cursor.getString(_cursorIndexOfCustomerName);
            final String _tmpCustomerPhone;
            _tmpCustomerPhone = _cursor.getString(_cursorIndexOfCustomerPhone);
            final String _tmpShippingAddress;
            _tmpShippingAddress = _cursor.getString(_cursorIndexOfShippingAddress);
            final String _tmpItems;
            _tmpItems = _cursor.getString(_cursorIndexOfItems);
            final long _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getLong(_cursorIndexOfTotalPrice);
            final String _tmpCustomNote;
            if (_cursor.isNull(_cursorIndexOfCustomNote)) {
              _tmpCustomNote = null;
            } else {
              _tmpCustomNote = _cursor.getString(_cursorIndexOfCustomNote);
            }
            final String _tmpCurrentStatus;
            _tmpCurrentStatus = _cursor.getString(_cursorIndexOfCurrentStatus);
            final long _tmpOrderDate;
            _tmpOrderDate = _cursor.getLong(_cursorIndexOfOrderDate);
            final long _tmpEstimatedCompletionDate;
            _tmpEstimatedCompletionDate = _cursor.getLong(_cursorIndexOfEstimatedCompletionDate);
            final String _tmpStatusHistory;
            _tmpStatusHistory = _cursor.getString(_cursorIndexOfStatusHistory);
            _result = new OrderEntity(_tmpId,_tmpCustomerName,_tmpCustomerPhone,_tmpShippingAddress,_tmpItems,_tmpTotalPrice,_tmpCustomNote,_tmpCurrentStatus,_tmpOrderDate,_tmpEstimatedCompletionDate,_tmpStatusHistory);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<OrderEntity>> getOrdersByDateRange(final long startOfDay, final long endOfDay) {
    final String _sql = "SELECT * FROM orders WHERE orderDate >= ? AND orderDate < ? ORDER BY orderDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"orders"}, new Callable<List<OrderEntity>>() {
      @Override
      @NonNull
      public List<OrderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerName = CursorUtil.getColumnIndexOrThrow(_cursor, "customerName");
          final int _cursorIndexOfCustomerPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "customerPhone");
          final int _cursorIndexOfShippingAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "shippingAddress");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final int _cursorIndexOfCustomNote = CursorUtil.getColumnIndexOrThrow(_cursor, "customNote");
          final int _cursorIndexOfCurrentStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStatus");
          final int _cursorIndexOfOrderDate = CursorUtil.getColumnIndexOrThrow(_cursor, "orderDate");
          final int _cursorIndexOfEstimatedCompletionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedCompletionDate");
          final int _cursorIndexOfStatusHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "statusHistory");
          final List<OrderEntity> _result = new ArrayList<OrderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final OrderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpCustomerName;
            _tmpCustomerName = _cursor.getString(_cursorIndexOfCustomerName);
            final String _tmpCustomerPhone;
            _tmpCustomerPhone = _cursor.getString(_cursorIndexOfCustomerPhone);
            final String _tmpShippingAddress;
            _tmpShippingAddress = _cursor.getString(_cursorIndexOfShippingAddress);
            final String _tmpItems;
            _tmpItems = _cursor.getString(_cursorIndexOfItems);
            final long _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getLong(_cursorIndexOfTotalPrice);
            final String _tmpCustomNote;
            if (_cursor.isNull(_cursorIndexOfCustomNote)) {
              _tmpCustomNote = null;
            } else {
              _tmpCustomNote = _cursor.getString(_cursorIndexOfCustomNote);
            }
            final String _tmpCurrentStatus;
            _tmpCurrentStatus = _cursor.getString(_cursorIndexOfCurrentStatus);
            final long _tmpOrderDate;
            _tmpOrderDate = _cursor.getLong(_cursorIndexOfOrderDate);
            final long _tmpEstimatedCompletionDate;
            _tmpEstimatedCompletionDate = _cursor.getLong(_cursorIndexOfEstimatedCompletionDate);
            final String _tmpStatusHistory;
            _tmpStatusHistory = _cursor.getString(_cursorIndexOfStatusHistory);
            _item = new OrderEntity(_tmpId,_tmpCustomerName,_tmpCustomerPhone,_tmpShippingAddress,_tmpItems,_tmpTotalPrice,_tmpCustomNote,_tmpCurrentStatus,_tmpOrderDate,_tmpEstimatedCompletionDate,_tmpStatusHistory);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getOrdersCountByDateRange(final long startOfDay, final long endOfDay,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM orders WHERE orderDate >= ? AND orderDate < ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfDay);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalRevenueByDateRange(final long startOfDay, final long endOfDay,
      final Continuation<? super Long> $completion) {
    final String _sql = "SELECT SUM(totalPrice) FROM orders WHERE orderDate >= ? AND orderDate < ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfDay);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            final Long _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
